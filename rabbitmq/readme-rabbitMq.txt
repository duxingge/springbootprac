

rabbitmq相关配置特性：
1。 mandatory: 强制投递：
开启强制消息投递（mandatory为设置为true），但消息未被路由至任何一个queue，
则回退一条消息到RabbitTemplate.ReturnCallback中的returnedMessage方法：

