# WineRevue

## Problem
Wine Revue, a small local cafe and wine bar in Lake Jackson, Texas, wants to expand their services to B2B catering and delivery. They don't want to pile too much on their plate, though (lol!)-- they want to start by sending an advertisement to all local businesses within a 5-mile radius of their venue. 

The [Brazosport Area Chamber of Commerce](https://brazosport.org/) website has a [member directory](https://brazosport.org/member-directory/) that lists all registered businesses in the Brazosport Area. This will be a good starting place for the task at hand-- if I can scrape the website for street addresses, I can then begin considering how to narrow my list down to a 5-mile radius. 

## Approach
This program uses the Maven Wizard in Eclipse to build a simple template and implement a dependency on Jsoup, a Java web-scraper package.
This [link](https://www.vogella.com/tutorials/EclipseMaven/article.html) was helpful to set Maven up. Then, I imported the Jsoup classes, nodes, and packages that I think I'll need. 

I was able to parse the html and transfer relevant information into results.csv, attached here. I gathered the owner names, street addresses, and towns of each business, storing null values where none were listed. 


