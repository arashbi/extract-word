import groovy.xml.MarkupBuilder
class Section {
    String title
    String text
}
class Name {
    static int file = 0
}

def rootNode = new XmlSlurper().parse("../clean.xml")
def sectionStarted = false
Section section
rootNode.'body'.children().each {
    if(sectionStarted){
        section.text = getText(it)
        write(section)
        sectionStarted = false;
    }
    if (!sectionStarted && it.name().equals('p')
        && it.children().size() ==1 ){
       def child = it.children()[0];
       if(child.name().equals('r') && child.children().size() == 1 ){
           def grandSon =  child.children()[0]
           if(grandSon.name().equals('t')){
               section = new Section()
               section.title = grandSon.text()
               sectionStarted = true
           }

        }
    }
}

def getText(node ) {
    def text = ''
    if (!node.text().trim().isEmpty()){
        text = text + ' ' + node.text().trim()
    }
    node.children().each {
        getText(it)
    }
    return text
}

def write(Section section) {
    print section.title
    def fileName = section.title.trim().find(/^[\d|\.]+/)
    if (fileName == null || fileName.trim().isEmpty()){
        fileName = Name.file++;
    }
    FileWriter writer = new FileWriter('../sections/'+fileName+'.xml')
    def build = new MarkupBuilder(writer)
    build.html {
        body {
            h1(section.title)
            p(section.text)
        }
        println writer.toString()
    }
}