package rest.data

interface ResData<in E> {

    fun preencherCom(entidade: E?)
}