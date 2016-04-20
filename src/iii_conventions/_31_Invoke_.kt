package iii_conventions

import util.TODO


class Invokable(private var counter: Int = 0) {
    operator fun invoke(): Invokable {
        counter++
        return this
    }

    final fun getNumberOfInvocations(): Int = counter

}

fun todoTask31(): Nothing = TODO(
        """
        Task 31.
        Change class Invokable to count the number of invocations (round brackets).
        Uncomment the commented code - it should return 4.
    """,
        references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {

    var invokable = Invokable(3)
    invokable()()()()()
    invokable.getNumberOfInvocations()

    //        todoTask31()
    return invokable()()()().getNumberOfInvocations()
}
