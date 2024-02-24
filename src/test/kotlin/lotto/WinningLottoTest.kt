package lotto

import lotto.constants.LottoPrize
import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.WinningLotto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() =
        assertThrows<IllegalArgumentException> {
            WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), LottoNumber(6))
        }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되지 않으면 예외가 발생하지 않는다`() =
        assertDoesNotThrow {
            WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), LottoNumber(7))
        }

    @ParameterizedTest
    @MethodSource("로또 당첨 결과 테스트 데이터")
    fun `로또의 당첨 결과를 확인한다`(
        winningLottoNumbers: List<Int>,
        bonusNumber: Int,
        expected: LottoPrize,
    ) {
        // given
        val winningLotto = WinningLotto(Lotto(winningLottoNumbers), LottoNumber(bonusNumber))
        val otherLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))

        // when
        val actual = winningLotto.getLottoPrize(otherLotto)

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `로또 당첨 결과 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6), 7, LottoPrize.FIRST),
                Arguments.of(listOf(1, 2, 3, 4, 5, 7), 6, LottoPrize.SECOND),
                Arguments.of(listOf(1, 2, 3, 4, 5, 7), 8, LottoPrize.THIRD),
                Arguments.of(listOf(1, 2, 3, 4, 7, 8), 9, LottoPrize.FOURTH),
                Arguments.of(listOf(1, 2, 3, 7, 8, 9), 10, LottoPrize.FIFTH),
                Arguments.of(listOf(10, 11, 12, 13, 14, 15), 16, LottoPrize.NOTHING),
            )
    }
}