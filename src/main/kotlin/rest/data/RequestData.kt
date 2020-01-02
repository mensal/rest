package rest.data

interface RequestData<in E> {

    fun escreverEm(entidade: E)
}