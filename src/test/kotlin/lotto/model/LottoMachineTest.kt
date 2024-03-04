package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)
    private val lottoSize = 5
    private val lottos =
        LottoMachine { lottoSize -> List(lottoSize) { Lotto.create(lottoNumbers) } }
            .generate(lottoSize)

    @Test
    fun `로또의 번호를 임의로 지정해주면 해당 번호로 로또가 발행된다`() {
        assertThat(lottos).allMatch { lotto ->
            lottoNumbers.all { LottoNumber.from(it) in lotto }
        }
    }

    @Test
    fun `지정해준 로또의 개수만큼 로또가 발행된다`() {
        val actual = lottos.size
        assertThat(actual).isEqualTo(lottoSize)
    }
}
