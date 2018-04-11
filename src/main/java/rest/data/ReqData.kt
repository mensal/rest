package rest.data

interface ReqData<in E> {

    fun escreverEm(entidade: E)
}