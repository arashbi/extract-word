import java.util.zip.*

docx = new File('obc.docx')
zip = new ZipFile(docx)
entry = zip.getEntry('word/document.xml')
stream = zip.getInputStream(entry)
//fos = new FileOutputStream("content.xml")
//fos << stream
//fos.close()
//stream.close()
// The namespace was gleaned from the decompressed XML.
wordMl = new XmlSlurper().parse(stream).declareNamespace(w: 'http://schemas.openxmlformats.org/wordprocessingml/2006/main')

// The outermost XML element node is assigned to the variable wordMl, so
// GPath expressions will start after that. To print out the concatenated
// descendant text nodes of w:body, you use:

//text = wordMl.'w:body'.children().collect { it.text() }.join('')
wordMl.'w:t'.each { print it}
//println text