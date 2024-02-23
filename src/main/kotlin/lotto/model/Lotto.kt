package lotto.model

import lotto.constants.LottoConstants.LOTTO_SIZE
import lotto.constants.LottoPrize
import lotto.constants.StringConstants.INVALID_LOTTO_NUMBER

class Lotto(numbers: List<Int>) {
    private val numbers: List<LottoNumber>

    init {
        require(numbers.isValidSize() && numbers.isNotDuplicate()) {
            INVALID_LOTTO_NUMBER
        }
        this.numbers = numbers.sorted().map { LottoNumber(it) }
    }

    private fun List<Int>.isValidSize() = size == LOTTO_SIZE

    private fun List<Int>.isNotDuplicate() = distinct().size == LOTTO_SIZE

    fun compare(
        otherLotto: Lotto,
        bonusNumber: LottoNumber,
    ): LottoPrize {
        val matchingCount = otherLotto.numbers.intersect(numbers).size
        val isMatchingBonus = contains(bonusNumber)
        return LottoPrize.getLottoPrize(matchingCount, isMatchingBonus)
    }

    fun contains(lottoNumber: LottoNumber) = numbers.contains(lottoNumber)

    override fun toString() = numbers.toString()
}
