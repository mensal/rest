package rest.data

interface ResponseData<E, S : ResponseData<E, S>> {

    fun ler(periodo: E?): S
}