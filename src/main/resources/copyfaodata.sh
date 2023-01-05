echo "Pepee"
df -h
exit 0
mkdir datazip
mkdir data
cd datazip
wget https://fenixservices.fao.org/faostat/static/bulkdownloads/FAOSTAT_A-S_E.zip .
wget https://fenixservices.fao.org/faostat/static/bulkdownloads/FAOSTAT_T-Z_E.zip .
ls -laR 
unzip FAOSTAT_A-S_E.zip -d ../data
unzip FAOSTAT_T-Z_E.zip -d ../data
cd ..
ls -laR
cd data
find . -name "*.zip" -exec unzip {} \;
ls -laR
cd ..
aws s3 sync datazip/  s3://fao-awsfoodaggparam/datazip/
aws s3 sync data/  s3://fao-awsfoodaggparam/data/
