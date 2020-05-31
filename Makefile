# Author:       nghiatc
# Email:        congnghia0609@gmail.com

.PHONY: gen
gen:
	@cd ./src/main/thrift; ./thrift_gen.sh; cd ../../..;



