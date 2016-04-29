set URL=http://localhost:4444/grid/register
:: this will be parametrize, reading from property, so that jenkins can run a complete environment making shell script.
java -jar SERVER -role node -hub %URL%
