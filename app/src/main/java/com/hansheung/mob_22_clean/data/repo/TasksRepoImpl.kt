package com.hansheung.mob_22_clean.data.repo

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hansheung.mob_22_clean.domain.model.Task
import com.hansheung.mob_22_clean.domain.repo.TasksRepo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class TasksRepoImpl(
    //private val db: CollectionReference = Firebase.firestore.collection("tasks")

    private val db: FirebaseFirestore = Firebase.firestore,
): TasksRepo {

    private fun getCollectionRef(): CollectionReference {
        return db.collection("tasks")
    }

    override fun getAllTasks(): Flow<List<Task>> = callbackFlow{

        //val listener = db.collection("tasks").addSnapshotListener{value, error->
        val listener = getCollectionRef().addSnapshotListener{value, error->
            if(error!=null){
                trySend(emptyList())
                return@addSnapshotListener
            }

            val tasks = mutableListOf<Task>()

            value?.documents?.forEach{doc ->
                val task = doc.toObject(Task::class.java)
                if(task!=null){
                    tasks.add(task.copy(id=doc.id))
                }
            }
            trySend(tasks)
        }
        awaitClose{
            listener.remove()
        }
    }

    override suspend fun getTask(id: String): Task? {
        val snapshot = getCollectionRef().document(id).get().await()
        return snapshot.toObject(Task::class.java)
    }

    override suspend fun addTasks(task: Task) {
        getCollectionRef().add(task).await()
    }

    override suspend fun delete(id: String) {
        getCollectionRef().document(id).delete().await()
    }
}