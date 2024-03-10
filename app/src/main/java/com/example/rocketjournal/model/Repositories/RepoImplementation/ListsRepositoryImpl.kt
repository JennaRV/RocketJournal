package com.example.rocketjournal.model.Repositories.RepoImplementation

import com.example.rocketjournal.model.DataTransferObjects.ListsDTO
import com.example.rocketjournal.model.Repositories.ListsRepository
import com.example.rocketjournal.model.dataModel.ListData
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListsRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
) : ListsRepository {
    override suspend fun createList(list: ListData): Boolean {
        return try {
            withContext(Dispatchers.IO){
                val listsDTO = ListsDTO(
                    user_id = list.user_id,
                    name = list.name,
                    is_complete = list.is_complete
                )
                postgrest.from("task_list").insert(listsDTO)
                true
            }
            true
        } catch (e: java.lang.Exception){
            throw e
        }
    }

    override suspend fun getLists(): List<ListsDTO>? {
        return withContext(Dispatchers.IO) {
            val result = postgrest.from("task_list")
                .select().decodeList<ListsDTO>()
            result
        }
    }

    override suspend fun getList(id: Int): ListsDTO {
        return withContext(Dispatchers.IO) {
            postgrest.from("task_list").select() {
                filter {
                    eq("list_id", id)
                }
            }.decodeSingle<ListsDTO>()
        }
    }

    override suspend fun deleteList(id: Int) {
        return withContext(Dispatchers.IO) {
            postgrest.from("task_list").delete {
                filter {
                    eq("list_id", id)
                }
            }
        }
    }

    override suspend fun updateList(
        list_id: Int,
        user_id: Int,
        name: String,
        is_complete: Boolean
    ) {
        withContext(Dispatchers.IO) {
            postgrest.from("task_list").update({
                set("is_complete", is_complete)
            }) {
                filter {
                    eq("list_id", list_id)
                }
            }
        }
    }
}