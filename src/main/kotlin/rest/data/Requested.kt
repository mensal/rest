package rest.data

interface Requested<in E> {

    fun escreverEm(entidade: E)
}