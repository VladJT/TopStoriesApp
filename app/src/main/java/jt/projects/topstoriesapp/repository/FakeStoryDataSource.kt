package jt.projects.topstoriesapp.repository


import jt.projects.topstoriesapp.model.Story

class FakeStoryDataSource : IStoryRepo {

    override suspend fun getStories(): List<Story> {
        return listOf(
            Story(
                title = "title 1",
                description = "Экс-аналитик ЦРУ Ларри Джонсон заявил в интервью YouTube-каналу Judging Freedom",
                imageUrl = ""
            ),
            Story(
                title = "title 2",
                description = "Мы расскажем о моделях мониторов, которые хорошо подойдут для редактирования фотографий в разных ценовых категориях",
                imageUrl = "https://avatars.dzeninfra.ru/get-zen_doc/1548443/pub_62d66c039dd65c036ab72ecf_62d66c059dd65c036ab72ef1/scale_1200"
            ),
        )

    }


}
