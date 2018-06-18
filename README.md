# KVStore
Key Value Store

The KEY VALUE STORE stores the values in key value format.

It has two APIS:
1. Put key value into the map.
    http://localhost:8080/KVStore/kvstore/set/class?value=master
    
    Structure : http://localhost:8080/KVStore/kvstore/set/{key}?value=master
    
2. Get the value for a given key from the store:
    http://localhost:8081/KVStore/kvstore/get/class
    Returns the value for key = class
    
    Structure: http://localhost:8081/KVStore/kvstore/get/{key}
    
    
Both the APIS works well in distributed manner, assuming that the distributed systems have the access to a common 
file directory where the distributed cache is placed.



