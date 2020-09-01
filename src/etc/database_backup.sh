#! /bin/bash

# backup-postgresql.sh
# by Craig Sanders &lt;cas@taz.net.au&gt;
# This script is public domain.  feel free to use or modify
# as you like.

DUMPALL='/usr/bin/pg_dumpall'
PGDUMP='/usr/bin/pg_dump'
PSQL='/usr/bin/psql'

# directory to save backups in, must be rwx by postgres user
BASE_DIR='/tmp/backups/postgres'
BACKUP_DIR='/media/pi/3e07d869-a085-48c1-8b43-8db6aa136fd2/postgres'
YMD=$(date "+%Y%m%d_%H%M%S")
DIR="$BASE_DIR/$YMD"

mkdir -p "$DIR"
cd "$DIR"

# get list of databases in system , exclude the tempate dbs
DBS=( $(sudo -u postgres $PSQL --list --tuples-only |
          awk '!/template[01]/ && $1 != "|" {print $1}') )

# first dump entire postgres database, including pg_shadow etc.
sudo -u postgres $DUMPALL --column-inserts | gzip -9 > "$DIR/db.out.gz"

# next dump globals (roles and tablespaces) only
sudo -u postgres $DUMPALL --globals-only | gzip -9 > "$DIR/globals.gz"

# now loop through each individual database and backup the
# schema and data separately
for database in "${DBS[@]}" ; do
    SCHEMA="$DIR/$database.schema.gz"
    DATA="$DIR/$database.data.gz"
    INSERTS="$DIR/$database.inserts.gz"

    # export data from postgres databases to plain text:

    # dump schema
    sudo -u postgres $PGDUMP --create --clean --schema-only "$database" |
        gzip -9 > "$SCHEMA"

    # dump data
    sudo -u postgres $PGDUMP --disable-triggers --data-only "$database" |
        gzip -9 > "$DATA"

    # dump data as column inserts for a last resort backup
    sudo -u postgres $PGDUMP --disable-triggers --data-only --column-inserts \
        "$database" | gzip -9 > "$INSERTS"

done

mv $DIR $BACKUP_DIR

# delete backup files older than 30 days
echo deleting old backup files:
find "$BACKUP_DIR/" -mindepth 1 -type d -mtime +30 -print0 |
    xargs -0r rm -rfv
