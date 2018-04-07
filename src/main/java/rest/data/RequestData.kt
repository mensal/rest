package rest.data

interface RequestData<E> {

    fun escrever(periodo: E?): E?
}