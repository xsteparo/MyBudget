package com.example.mybudget.data.repository.firestore

import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.data.model.db.Note
import com.example.mybudget.data.utils.Constants.FIRESTORE_NOTES_REFERENCE
import com.example.mybudget.data.utils.await
import com.example.mybudget.data.utils.serializeToMap
import com.example.mybudget.data.utils.toDataClass
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.time.LocalDateTime
import javax.inject.Inject

internal class FirebaseFirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : FirebaseFirestoreRepository {
    override val notes: CollectionReference
        get() = firebaseFirestore.collection(FIRESTORE_NOTES_REFERENCE)

    override suspend fun addNewNote(note: Note): Resource<String> {
        return try {
            val ref = notes.document()
            val noteData = note.serializeToMap().toMutableMap()
            noteData["createdAt"] = FieldValue.serverTimestamp()
            noteData["editedAt"] = FieldValue.serverTimestamp()
            noteData["id"] = ref.id
            ref.set(noteData).await()
            Resource.Success(ref.id)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun getNotes(userId: String, daysBefore: Long?): Resource<List<Note>> {
        return try {
            val result = if (daysBefore==null) {
                notes.orderBy("createdAt", Query.Direction.DESCENDING).whereEqualTo("userId", userId).get().await()
            } else {
                val test = LocalDateTime.now().minusDays(daysBefore)
                val prevDate = Timestamp(test.second.toLong(), test.nano)
                notes.whereEqualTo("userId", userId).whereGreaterThanOrEqualTo("createdAt", prevDate).get().await()
            }
            val notes = result?.documents?.map { doc -> doc.data?.toDataClass() as Note }.orEmpty()
            Resource.Success(notes)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun editNote(note: Note): Resource<String> {
        return try {
            val ref = notes.document(note.id)
//            val noteData = note.serializeToMap().toMutableMap()
//            noteData["editedAt"] = FieldValue.serverTimestamp()
//            ref.set(noteData).await()
            ref
                .update(
                    "editedAt", FieldValue.serverTimestamp(),
                    "title", note.title,
                    "productType", note.productType,
                    "location", note.location,
                    "amount", note.amount,
                    "imageUrl", note.imageUrl,
                )
                .await()
            Resource.Success(ref.id)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun deleteNote(id: String): Resource<String> {
        return try {
            notes.document(id).delete().await()
            Resource.Success(id)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}