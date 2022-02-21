package com.picpay.desafio.android.data.mapper.user

import com.picpay.desafio.android.data.local.db.Mocks
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import kotlin.test.assertTrue

class UserEntityMapperTest {

    @Test
    fun `transform User into UserEntity`() {
        assertTrue {
            UserEntityMapper.transform(Mocks.userMock) ==  Mocks.userEntityMock
        }
    }

    @Test
    fun `transform User List into UserEntity List and compare if values are same`() {
        val userListMock = Mocks.userListMock
        val userEntityList = UserEntityMapper.transformList(userListMock)

        userEntityList.forEachIndexed { index, userEntity ->
            run {
                assertThat(userEntity.id, `is`(userListMock[index].id))
                assertThat(userEntity.img, `is`(userListMock[index].img))
                assertThat(userEntity.name, `is`(userListMock[index].name))
                assertThat(userEntity.username, `is`(userListMock[index].username))
            }
        }
    }

}