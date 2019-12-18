# PAM-Geography

PAM-Geography is a simple utility library for lookup of geographic data such as:
Norwegian postal codes, Norwegian municipality and county names, and country
names.

*NB release versions >= 2.0-kor2020 has postal data from the new municipality/county
mergers that is only valid after 1st of January 2020. Use release 1.9 if
unsure.*

## Data sources
### List of ISO-3166 countries

Unaltered dataset from SSB (see Standard for landkoder utenrikshandel)

Last update: see git log

https://www.ssb.no/klass/klassifikasjoner/100


### Norwegian postal codes, county  codes, cities

Unaltered dataset from Norwegian Postal Service

Last update: see git log.

https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer
https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer/postnummertabeller-veiledning

Recipe for updating the postal codes data file using the command line:

1.  Download and transform updated version of `src/main/resources/postal_codes_no.tsv`:

        $ cd src/main/resources
        $ curl https://www.bring.no/postnummerregister-ansi.txt | \
               iconv -f iso-8859-1 -t utf-8 | \
               tr -d \\r > postal_codes_no.tsv.new

    The file is distributed as an ANSI-encoded text file with tab chars as field
    separator and Windows style newlines. This project requires the file to be
    in UTF-8-encoding and preferably use Unix newlines.
        
    Verify diff, to notice if anything is fundametally wrong or changed with
    upstream data or format:

        $ diff -u postal_codes_no.tsv postal_codes_no.tsv.new
        
    Move into place:
    
        $ mv postal_codes_no.tsv.new postal_codes_no.tsv
        $ git add .
        $ ...
        
2.  Also ensure that Norwegian county data is updated. This is currently present
    in Java code, in the class `no.nav.pam.geography.CountyDAO`.
    
    The counties and official codes are listed on the page:

    https://www.bring.no/radgivning/sende-noe/adressetjenester/postnummer/postnummertabeller-veiledning

3.  Release new version of pam-geography with updated data.

### Release new version
`mvn release:prepare`
`mvn release:perform`
`git push`
