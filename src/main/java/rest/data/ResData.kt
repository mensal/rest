package rest.data

interface ResData<E, S : ResData<E, S>> {

    fun ler(periodo: E?): S
}