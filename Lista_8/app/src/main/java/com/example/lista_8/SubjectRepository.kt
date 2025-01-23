//package com.example.lista_8.data
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.lista_8.Subject
//
//class SubjectRepository(private val subjectDao: SubjectDao) {
//
//    private val _subjectList = MutableLiveData<List<Subject>>()
//    val subjectList: LiveData<List<Subject>> get() = _subjectList
//
//    suspend fun getAllSubjects(): List<SubjectEntity> {
//        return subjectDao.getAllSubjects()
//    }
//
//    suspend fun addSubject(subject: SubjectEntity) {
//        subjectDao.insert(subject)
//        _subjectList.postValue(getAllSubjects().map { it.toSubject() }) // Update the LiveData
//    }
//
//    suspend fun updateSubject(subject: SubjectEntity) {
//        subjectDao.update(subject)
//        _subjectList.postValue(getAllSubjects().map { it.toSubject() }) // Update the LiveData
//    }
//
//    suspend fun deleteSubject(subject: SubjectEntity) {
//        subjectDao.delete(subject)
//        _subjectList.postValue(getAllSubjects().map { it.toSubject() }) // Update the LiveData
//    }
//
//    private fun SubjectEntity.toSubject(): Subject {
//        return Subject(name, grade)
//    }
//}
