# ntc-jthrift
ntc-jthrift is an example java thrift

## HAProxy Config Load Balancer for Thrift Server
```bash
frontend thrift_fe
	bind *:9000
	mode tcp
	option tcplog
	default_backend thrift_be

backend thrift_be
	mode tcp
	balance roundrobin
	option tcp-check
	server thift-go 127.0.0.1:9090 check
	server thrift-java 127.0.0.1:9091 check
```

## License
This code is under the [Apache License v2](https://www.apache.org/licenses/LICENSE-2.0).  
