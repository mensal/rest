package rest.data

interface Data<E> {

    fun ler(periodo: E?): Data<E>

    fun escrever(periodo: E?): E?
}