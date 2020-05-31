#!/bin/bash
# Author:       nghiatc
# Email:        congnghia0609@gmail.com

PATH_NAMESPACE=com/ntc/thrift/tutorial


# 1. Delete old file thrift gen.
echo "1. Delete old file thrift gen."
rm -rf ../java/$PATH_NAMESPACE
mkdir -p ../java/$PATH_NAMESPACE


# 2. Thrift gen-java
echo "2. Thrift gen-java"
thrift --gen java shared.thrift
thrift --gen java tutorial.thrift


# 3. Move gen-java to main java.
echo "3. Move gen-java to main/java/"
mv gen-java/$PATH_NAMESPACE/* ../java/$PATH_NAMESPACE/
# Delete gen-java
rm -rf gen-java

echo "Thrift gen-java complete."