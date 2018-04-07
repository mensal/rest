package rest.data

interface ReqData<E> {

    fun escrever(entidade: E?): E?
}