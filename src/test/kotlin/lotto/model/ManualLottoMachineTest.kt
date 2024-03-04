package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoMachineTest {
    private val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)
    private val lottoSize = 2
    private val lottos =
        ManualLottoMachine(listOf(lottoNumbers, lottoNumbers))
            .generate(lottoSize)

    @Test
    fun `수동 로또의 번호를 임의로 지정해주면 해당 번호로 로또가 발행된다`() {
        assertThat(lottos).allMatch { lotto ->
            lottoNumbers.all { LottoNumber.from(it) in lotto }
        }
    }

    @Test
    fun `지정해준 수동 로또의 개수만큼 로또가 발행된다`() {
        val actual = lottos.size
        assertThat(actual).isEqualTo(lottoSize)
    }
}
