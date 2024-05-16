#!/usr/bin/env bash
set -e

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
DESTINATION="${SCRIPT_DIR}/target/doc"
rm -rf $DESTINATION
mkdir -p "$DESTINATION"

echo "Destination is $DESTINATION"
gradle javadoc-params
pushd app
pwd
javadoc -d $DESTINATION --ignore-source-errors -sourcepath src/main/java -subpackages at @build/javadoc.txt
popd
echo "Documentation written to $DESTINATION"
open "$DESTINATION/index.html"
