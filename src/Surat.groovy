import groovy.xml.MarkupBuilder

/**
 * Created by emhah on 11/30/2015.
 */

class Surat {
    String toText
    String fromText
    String body
    String pengirimText
    String penerimaText
    String prihalText
    String penutupText
    def sections = []

    /**
     * This method accepts a closure which is essentially the DSL. Delegate the closure methods to
     * the DSL class so the calls can be processed
     */

    def static make(closure) {
        Surat surat = new Surat()
        // any method called in closure will be delegated to the memoDsl class
        closure.delegate = surat
        closure()
    }

    /**
     * Store the parameter as a variable and use it later to output a memo
     */
    def to(String toText) {
        this.toText = toText
    }

    def pengirim(String pengirimText){
        this.pengirimText = pengirimText;
    }

    def penerima(String pengirimText){
        this.penerimaText = pengirimText;
    }

    def prihal(String pengirimText){
        this.prihalText = pengirimText;
    }

    def penutup(String pengirimText){
        this.penutupText = pengirimText;
    }

    def from(String fromText) {
        this.fromText = fromText
    }

    def body(String bodyText) {
        this.body = bodyText
    }

    def getHtml() {
        doHtml(this)
    }

    def getText() {
        doText(this)
    }

    private static doHtml(Surat surat){
        StringWriter writer = new StringWriter()
        MarkupBuilder xml = new MarkupBuilder(writer)

        String filename = "C:/xampp/htdocs/test.html"
        def surats = new File(filename)
        PrintWriter printWriter = new PrintWriter(surats)
        if (surat.prihalText == "SKPI") {
            xml.html() {
                head {
                    title("SKPI")
                }
                body {
                    form(method: 'post', action: 'surat-maker/enginetest.php') {
                        br()
                        input(type: 'hidden', name: 'pengirim', value: surat.pengirimText)
                        label("nama")
                        br()
                        input(type: 'text', name: 'nama')
                        br()
                        label("nim")
                        br()
                        input(type: 'text', name: 'nim')
                        br()
                        label("Program Studi")
                        br()
                        input(type: 'text', name: 'prodi')
                        br()
                        label("kontribusi")
                        br()
                        input(type: 'text', name: 'kontribusi')
                        br()
                        input(type: 'submit', value: 'submit')
                    }
                }
            }
            printWriter.println(writer.toString())
            printWriter.close()
            println writer
        }
    }

    private static doText(Surat memoDsl) {

        String template = "<?php\n\n"
        template +="require_once 'common.php';\n\n"
        template += "system('clear');\n"
        template += "print_r(\$_POST);\n"
        template += "print(Demos_Zend_Service_LiveDocx_Helper::wrapLine("
        template += "        PHP_EOL . 'huhuhu'."
        template += "                PHP_EOL ."
        template += "                PHP_EOL)"
        template += ");\n"
        template += "\$mailMerge = new Zend_Service_LiveDocx_MailMerge();\n"
        template += "\$mailMerge->setUsername(DEMOS_ZEND_SERVICE_LIVEDOCX_USERNAME)"
        template += "->setPassword(DEMOS_ZEND_SERVICE_LIVEDOCX_PASSWORD);"

        if (memoDsl.prihalText == "SKPI"){
            template += "\$mailMerge->setLocalTemplate('skpi.docx');\n"
            template += "\$nama = \$_POST['nama'];\n"
            template += "\$nim = \$_POST['nim'];\n"
            template += "\$prodi = \$_POST['prodi'];\n"
            template += "\$pengirim = \$_POST['pengirim'];\n"
            template += "\$kontribusi = \$_POST['kontribusi'];\n"
            template += "\$mailMerge->assign('nama',  \$nama);\n"
            template += "\$mailMerge->assign('nim',  \$nim);\n"
            template += "\$mailMerge->assign('prodi',  \$prodi);\n"
            template += "\$mailMerge->assign('PENGIRIM',  \$pengirim . \"\\n  huhuhuaasas\");\n"
            template += "\$mailMerge->assign('kontribusi',  \$kontribusi);\n"
            template += "\$mailMerge->createDocument();\n"
            template += "\$document = \$mailMerge->retrieveDocument('pdf');\n"
            template += "file_put_contents('document.pdf', \$document);\n"
            template += "print('File Telah Siap Di Download ');\n"
            template += "unset(\$mailMerge);\n"
            template += "?>\n"
            template += "<br><br>"
            template += "Download di <a href='document.pdf'>sini</a>"
        }
        String filename = "C:/xampp/htdocs/surat-maker/enginetest.php"
        def surats = new File(filename)
        PrintWriter printWriter = new PrintWriter(surats)

        printWriter.println(template.toString())
        printWriter.close()
        println template
    }

}