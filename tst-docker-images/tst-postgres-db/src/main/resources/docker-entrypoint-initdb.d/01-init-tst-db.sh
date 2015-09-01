#!/bin/bash
set -e

echo "#############################"
echo "Installing tst-db data"
echo "#############################"

export PG_PASSWORD=$POSTGRES_PASSWORD

echo "Creating database tstpgdb"
psql --username postgres <<-EOSQL
create database tstpgdb encoding 'UTF-8';
EOSQL

if [ -f /docker-entrypoint-initdb.d/testdata/testdata.dmp ]; then
   pg_restore --no-owner -d "$POSTGRES_USER" -U "$POSTGRES_USER" /docker-entrypoint-initdb.d/testdata/testdata.dmp
   echo "Testdata restored"

else
   if [ -d /docker-entrypoint-initdb.d ]; then
        echo "Running sql scripts"
        for f in /docker-entrypoint-initdb.d/sql-scripts/*.sql; do
            [ -f "$f" ] && echo $f; psql -P pager=off -U "$POSTGRES_USER" -q -n -f $f
        done
        echo "Finished running sql scripts"
   fi
fi

export PG_PASSWORD=

echo "#############################"
echo "Installing tst-db data complete"
echo "#############################"
