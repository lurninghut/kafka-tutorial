## Kafka producer config
[Kafka Internal Documentation Link](https://jaceklaskowski.gitbooks.io/apache-kafka/content/)
### ack
The config is related to leader and followers.\
If **ack=0** then 
producer will not wait for any acknowledgement from server.\
**ack=1** [default] means, leader will respond after writing to its own log 
and without waiting for followers to complete writing.\
**ack=all** or ack=-1 is when leader will wait for all in-sync replicas
to acknowledge the record. This is strongest guarantee.
