package rest.data

interface ReqData<E> {

    fun escrever(periodo: E?): E?
}