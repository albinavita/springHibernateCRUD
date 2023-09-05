package org.example.dao;

import org.example.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

   //возвращает список из объектов класса Рerson
    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
       return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    //возвращает одного чел по id
    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
       return  session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
    Session session = sessionFactory.getCurrentSession();
    Person person = session.get(Person.class, id);
    person.setName(updatePerson.getName());
    person.setAge(updatePerson.getAge());
    person.setEmail(updatePerson.getEmail());
    }

    public void delete (int id) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(session.get(Person.class, id));

    }

}
