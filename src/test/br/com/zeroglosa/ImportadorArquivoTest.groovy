package br.com.zeroglosa

/**
 * Created by felansu on 6/18/15.
 */
class ImportadorArquivoTest extends GroovyTestCase {

    private ImportadorArquivo importadorArquivo

    void setUp() {
        importadorArquivo = new ImportadorArquivo()
    }

    private File obtemArquivo(String nome) {
        File arquivo = new File("/home/felansu/IdeaProjects/importadordearquivos/src/$nome")
        arquivo
    }

    void "test Nome do layout vazio lança exceção"() {
        shouldFail(IllegalArgumentException) {
            importadorArquivo.obterRegistros(obtemArquivo('pagamento.txt'), "")
        }
    }

    void "test Nome do layout nulo lança exceção"() {
        shouldFail(IllegalArgumentException) {
            importadorArquivo.obterRegistros(obtemArquivo('pagamento.txt'), null)
        }
    }

    void "test Arquivo 'txt' do tipo Pagamento retorna lista de mapa"() {
        List<Map> dadosArquivo = importadorArquivo.obterRegistros(obtemArquivo('pagamento.txt'), "fibo-layout")
        assertNotNull dadosArquivo
    }

    void "test arquivo sem conteudo lança exceção"(){
        shouldFail(IllegalArgumentException) {
            importadorArquivo.obterRegistros(obtemArquivo('pagamentoVazio.txt'), "fibo-layout")
        }
    }

    void "test Layout de pagamento minimo retorna registros"(){
        List<Map> valorEsperado= [[tipo: 'guia', Senha: '4469552', Matrícula: '967613', Nome: 'DIVANI FLORENCIO LACERDA',
                                   'Data de atendimento': '21/03/2015']]

        List<Map> retorno = importadorArquivo.obterRegistros(obtemArquivo('pagamentoUmaLinha.txt'), "fibo-layout")
        assertEquals(valorEsperado,retorno)

    }
}
