# PAM-Geography

PAM-Geography is a simple utility library for lookup of geographic data such as: norwegian postal codes, norwegian county names, and country names.


### Sources:
#### List of ISO-3166 countries
Unaltered dataset from SSB (see Standard for landkoder utenrikshandel)  
Last update: 30.08.2018  
https://www.ssb.no/klass/klassifikasjoner/100

#### Norwegian postal codes, county  codes, cities
Unaltered dataset from Norwegian Post  
Last update: 30.08.2018  
https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer  
https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer/postnummertabeller-veiledning

### Release new version:
`mvn release:prepare`
`mvn release:perform`
`git push`
