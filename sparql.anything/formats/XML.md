<!-- This page has been generated with sparql-anything-documentation-generator module -->

# XML

The following analysis refers to the [Document Object Model (DOM) specification](https://dom.spec.whatwg.org).
XML elements (also known as tags) can be definitely considered containers, so we can reuse both the `rdf:Property` component for specifying tag attributes, and container membership properties for specifying relations to child elements in the DOM tree.
These may include text, which are expressed as RDF literals of type `xsd:string`. 
Tag names are represented as RDF types: `rdf:type`.
SPARQL Anything reuses namespaces declared within the original document to name properties and types, when available, otherwise fallbacks to the default `xyz:`.

!!! note
	XML attribute values are always interpreted as literals, even if they are supposed to be QName in a referred XML schema. See also [this comment](https://github.com/SPARQL-Anything/sparql.anything/issues/322#issuecomment-1351299515).



## Extensions

SPARQL Anything selects this transformer for the following file extensions:

- svg
- svgz
- xml

## Media types

SPARQL Anything selects this transformer for the following media types:

- application/xml
- image/svg+xml
- text/xml

## Default implementation

- [io.github.sparqlanything.xml.XMLTriplifier](../sparql-anything-xml/src/main/java/io/github/sparqlanything/xml/XMLTriplifier.java)

## Default Transformation

### Data

```XML
<?xml version="1.0" ?>
<xx:Element xmlns:xx="http://www.example.org">
	<xx:someThing>Hallo world</xx:someThing>
	<xx:someThingElse xx:key="0.1"/>
</xx:Element>




```

Located at https://sparql-anything.cc/examples/simple.xml

### Query

```
CONSTRUCT 
  { 
    ?s ?p ?o .
  }
WHERE
  { SERVICE <x-sparql-anything:location=https://sparql-anything.cc/examples/simple.xml>
      { GRAPH ?g
          { ?s  ?p  ?o }
      }
  }

```

### Facade-X RDF

```turtle
PREFIX dc:     <http://purl.org/dc/elements/1.1/>
PREFIX eg:     <http://www.example.org/>
PREFIX fx:     <http://sparql.xyz/facade-x/ns/>
PREFIX ja:     <http://jena.hpl.hp.com/2005/11/Assembler#>
PREFIX owl:    <http://www.w3.org/2002/07/owl#>
PREFIX rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:   <http://www.w3.org/2000/01/rdf-schema#>
PREFIX rss:    <http://purl.org/rss/1.0/>
PREFIX vcard:  <http://www.w3.org/2001/vcard-rdf/3.0#>
PREFIX whatwg: <https://html.spec.whatwg.org/#>
PREFIX xhtml:  <http://www.w3.org/1999/xhtml#>
PREFIX xsd:    <http://www.w3.org/2001/XMLSchema#>
PREFIX xyz:    <http://sparql.xyz/facade-x/data/>

[ rdf:type  fx:root , <http://www.example.org#Element>;
  rdf:_1    [ rdf:type  <http://www.example.org#someThing>;
              rdf:_1    "Hallo world"
            ];
  rdf:_2    [ rdf:type                      <http://www.example.org#someThingElse>;
              <http://www.example.org#key>  "0.1"
            ]
] .

```
## Options

### Summary

| Option name | Description | Valid Values | Default Value |
|-------------|-------------|--------------|---------------|
| [xml.path](#xmlpath) | One or more XPath expressions as filters. E.g. `xml.path=value` or `xml.path.1`, `xml.path.2`,`...` to add multiple expressions. | Any valid XPath | Not set |

---
### `xml.path`

#### Description

One or more XPath expressions as filters. E.g. `xml.path=value` or `xml.path.1`, `xml.path.2`,`...` to add multiple expressions.

#### Valid Values

Any valid XPath

#### Default Value

Not set

#### Examples

##### Example 1



###### Input

```XML
<?xml version="1.0" encoding="UTF-8"?>
<breakfast_menu>
	<food>
		<name>Belgian Waffles</name>
		<price>$5.95</price>
		<desc>Two of our famous Belgian Waffles with plenty of real maple syrup</desc>
		<calories>650</calories>
	</food>
	<food>
		<name>Strawberry Belgian Waffles</name>
		<price>$7.95</price>
		<desc>Light Belgian waffles covered with strawberries and whipped cream</desc>
		<calories>900</calories>
	</food>
</breakfast_menu>

```

https://sparql-anything.cc/examples/simple-menu.xml

###### Query

```
PREFIX  fx:   <http://sparql.xyz/facade-x/ns/>

CONSTRUCT 
  { 
    ?s ?p ?o .
  }
WHERE
  { SERVICE <x-sparql-anything:>
      { fx:properties
                  fx:location     "https://sparql-anything.cc/examples/simple-menu.xml" ;
                  fx:xml.path     "//food" ;
                  fx:blank-nodes  false .
        ?s        ?p              ?o
      }
  }

```

###### Result

```turtle
PREFIX dc:     <http://purl.org/dc/elements/1.1/>
PREFIX eg:     <http://www.example.org/>
PREFIX fx:     <http://sparql.xyz/facade-x/ns/>
PREFIX ja:     <http://jena.hpl.hp.com/2005/11/Assembler#>
PREFIX owl:    <http://www.w3.org/2002/07/owl#>
PREFIX rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs:   <http://www.w3.org/2000/01/rdf-schema#>
PREFIX rss:    <http://purl.org/rss/1.0/>
PREFIX vcard:  <http://www.w3.org/2001/vcard-rdf/3.0#>
PREFIX whatwg: <https://html.spec.whatwg.org/#>
PREFIX xhtml:  <http://www.w3.org/1999/xhtml#>
PREFIX xsd:    <http://www.w3.org/2001/XMLSchema#>
PREFIX xyz:    <http://sparql.xyz/facade-x/data/>

<https://sparql-anything.cc/examples/simple-menu.xml#/2:food/4:calories>
        rdf:type  xyz:calories;
        rdf:_1    "900" .

<https://sparql-anything.cc/examples/simple-menu.xml#/2:food/3:desc>
        rdf:type  xyz:desc;
        rdf:_1    "Light Belgian waffles covered with strawberries and whipped cream" .

<https://sparql-anything.cc/examples/simple-menu.xml#/2:food/2:price>
        rdf:type  xyz:price;
        rdf:_1    "$7.95" .

<https://sparql-anything.cc/examples/simple-menu.xml#/2:food/1:name>
        rdf:type  xyz:name;
        rdf:_1    "Strawberry Belgian Waffles" .

<https://sparql-anything.cc/examples/simple-menu.xml#/2:food>
        rdf:type  xyz:food;
        rdf:_1    <https://sparql-anything.cc/examples/simple-menu.xml#/2:food/1:name>;
        rdf:_2    <https://sparql-anything.cc/examples/simple-menu.xml#/2:food/2:price>;
        rdf:_3    <https://sparql-anything.cc/examples/simple-menu.xml#/2:food/3:desc>;
        rdf:_4    <https://sparql-anything.cc/examples/simple-menu.xml#/2:food/4:calories> .

<https://sparql-anything.cc/examples/simple-menu.xml#/1:food/4:calories>
        rdf:type  xyz:calories;
        rdf:_1    "650" .

<https://sparql-anything.cc/examples/simple-menu.xml#/1:food/3:desc>
        rdf:type  xyz:desc;
        rdf:_1    "Two of our famous Belgian Waffles with plenty of real maple syrup" .

<https://sparql-anything.cc/examples/simple-menu.xml#/1:food/2:price>
        rdf:type  xyz:price;
        rdf:_1    "$5.95" .

<https://sparql-anything.cc/examples/simple-menu.xml#/1:food/1:name>
        rdf:type  xyz:name;
        rdf:_1    "Belgian Waffles" .

<https://sparql-anything.cc/examples/simple-menu.xml#/1:food>
        rdf:type  xyz:food;
        rdf:_1    <https://sparql-anything.cc/examples/simple-menu.xml#/1:food/1:name>;
        rdf:_2    <https://sparql-anything.cc/examples/simple-menu.xml#/1:food/2:price>;
        rdf:_3    <https://sparql-anything.cc/examples/simple-menu.xml#/1:food/3:desc>;
        rdf:_4    <https://sparql-anything.cc/examples/simple-menu.xml#/1:food/4:calories> .

<https://sparql-anything.cc/examples/simple-menu.xml#>
        rdf:type  fx:root;
        rdf:_1    <https://sparql-anything.cc/examples/simple-menu.xml#/1:food>;
        rdf:_2    <https://sparql-anything.cc/examples/simple-menu.xml#/2:food> .

```





