# Author:       nghiatc
# Email:        congnghia0609@gmail.com
# Thrift:       0.13.0
# Since:        May 31, 2020

.PHONY: gen
gen:
	@cd ./src/main/thrift; ./thrift_gen.sh; cd ../../..;



