package iii_conventions

import kotlin.comparisons.compareValuesBy

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return compareValuesBy(this, other, { it.year }, { it.month }, { it.dayOfMonth })
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(interval: TimeInterval): MyDate = addTimeIntervals(interval, 1)

operator fun MyDate.plus(interval: RepeatedTimeInterval): MyDate = addTimeIntervals(interval.timeInterval, interval.howMany)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(x: Int) : RepeatedTimeInterval = RepeatedTimeInterval(this, x)

class RepeatedTimeInterval(val timeInterval: TimeInterval, val howMany : Int)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var last = start;
            override fun next(): MyDate {
                last = last.nextDay()
                return last;
            }

            override fun hasNext(): Boolean {
                return last >= endInclusive
            }
        }
    }
}
