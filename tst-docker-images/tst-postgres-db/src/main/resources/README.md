
## TenderNed Postgres image

This project builds a docker container the TenderNed Postgres


Before running the build:

1. Install [Docker](https://www.docker.io/gettingstarted/#1)

Once you have completed steps 1..4 you can build an image using the following command:

		$ docker build -t tenderned/db-integration-test:1.0-SNAPSHOT .



You can run the container with the following command:

        $ docker run --name tenderned-db-daemon -e POSTGRES_USER=tenderned -e POSTGRES_PASSWORD=postgres \
                       -e TD_EXTRA_USER1_NAME=qry_user_cms -e TD_EXTRA_USER1_PWD=smc_resu_yrq -e TD_EXTRA_USER2_NAME=tenderned_server -e TD_EXTRA_USER2_PWD=postgres \
                       -d tenderned/db-integration-test:1.0-SNAPSHOT


Connect to the container using the command below (with password = 'postgres' as specified above)

		$ docker run -it --link tenderned-db-daemon:tdserver --rm postgres:9.3 sh -c 'exec psql -h tdserver -p 5432 -U tenderned'


Note: you can also run the container as a shell

        $ docker run -P -it -e POSTGRES_USER=tenderned -e POSTGRES_PASSWORD=postgres \
                             -e TD_EXTRA_USER1_NAME=qry_user_cms -e TD_EXTRA_USER1_PWD=smc_resu_yrq -e TD_EXTRA_USER2_NAME=tenderned_server -e TD_EXTRA_USER2_PWD=postgres \
                             -d tenderned/db-integration-test:1.0-SNAPSHOT bash




        >> 31bc

        $ docker attach 31bc (and press return twice)
        $ /docker-entrypoint.sh postgres


And (since docker 1.3) you can connect to a running container using:

        $ docker exec -it <<container-id>> bash
