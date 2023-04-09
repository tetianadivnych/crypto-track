# Crypto Track Application

This project is designed to get up-to-date information about prices for cryptocurrencies from CEX.IO(https://cex.io/). The system fetches the last prices in USD for Bitcoin(BTC), Ethereum(ETH) and Ripple (XRP) every 30 minutes, and adds them to a database for further management.

### Functionality
The project also contains custom endpoints in order to:
- get a record with the lowest price of the selected cryptocurrency
- get a record with the highest price of BTC, ETH, or XRP cryptocurrency
- get a selected page with a selected number of elements and default sorting by price from lowest to highest

Some custom exceptions are created and applied to provide additional information should some things get out of client control.

### Technologies Used 
- Java 11
- Spring Boot: Spring Web, Spring Data
- MongoDB
- CEX.IO API






