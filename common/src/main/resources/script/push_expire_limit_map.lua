-- 构造一个有过期时间且限制大小的map
-- 每次试图往该map加入新属性，都会先淘汰已过期的属性。当该属性存在或者属性数目小于max size时，才会更新/插入新属性，返回true,否则返回false
-- 当过期时间最晚的一个属性过期后，整个map都会过期
-- 返回是否成功插入/更新
-- KEYS[1] map key
-- ARGV[1] field 属性名称
-- ARGV[2] map max properties size
-- ARGV[3] timeout 过期时间
local map = KEYS[1]
local field = ARGV[1]
local map_max_size = tonumber(ARGV[2])
local timeout = tonumber(ARGV[3])
local future_ts = 2000000000
redis.call('setnx', 'future', 1)
redis.call('expireat', 'future', future_ts)
local current_ts = future_ts - redis.call('ttl', 'future')
local keys = redis.call('HKEYS', map)
local longest_live_till = current_ts + timeout
local exits = false
for i,v in ipairs(keys) do
    local live_till = tonumber(redis.call('HGET', map, keys[i]))
    if live_till < current_ts then
        redis.call('HDEL', map, keys[i])
    else
        if(keys[i]==field) then
            exits = true
        end
        if live_till > longest_live_till then
            longest_live_till = live_till
        end
    end
end

local llen = redis.call("HLEN", map)
if (llen < map_max_size or exits) then
    redis.call('HMSET', map, field, timeout + current_ts)
    local ttl = tonumber(redis.call('TTL', map))
    if ttl == -1 then
        redis.call('EXPIRE', map, timeout)
    elseif ttl < longest_live_till - current_ts then
        redis.call('PERSIST', map)
        redis.call('EXPIRE', map, longest_live_till - current_ts)
    end
    return true
else
    return false
end


