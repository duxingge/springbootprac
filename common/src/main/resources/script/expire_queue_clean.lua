-- 结构与push_expire_queue相同,但不插入记录
-- 构造一个有过期时间且限制大小的queue
-- 每次计算时，都会先淘汰已过期的元素
-- 当过期时间最晚的一个元素过期后，整个queue都会过期
-- 返回剩余list size
-- KEYS[1] list key
local list = KEYS[1]
local future_ts = 2000000000
redis.call('setnx', 'future', 1)
redis.call('expireat', 'future', future_ts)
local current_ts = future_ts - redis.call('ttl', 'future')
local llen = redis.call("LLEN", list)
for i = 1, llen do
    local live_till = tonumber(redis.call("LPOP", KEYS[1]))
    if live_till > current_ts then
        redis.call("RPUSH", list, live_till)
    end
end
llen = redis.call("LLEN", list)
return llen
