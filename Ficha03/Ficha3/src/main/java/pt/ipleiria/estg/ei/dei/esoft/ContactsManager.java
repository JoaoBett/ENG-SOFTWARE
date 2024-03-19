package pt.ipleiria.estg.ei.dei.esoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ContactsManager {
    private List<Contact> contacts;
    private HashMap<String, List<Contact>> labels;
    public ContactsManager() {
        contacts = new LinkedList<>();
        labels = new HashMap<>(200);
    }
    public List<String> getLabels() {
        return new ArrayList<>(labels.keySet());
    }

    public List<Contact> getContacts(String... labels) {
        if (labels.length == 0) {
            return new ArrayList<>(contacts);
        }

        List<Contact> result = new ArrayList<>();
        for (String label : labels) {
            List<Contact> contactsInLabel = this.labels.get(label);
            if (contactsInLabel != null) {
                result.addAll(contactsInLabel);
            }
        }
        return result;
    }

    public List<Contact> search(String term, String... labels) {
        List<Contact> result = new ArrayList<>();
        if (labels.length == 0) {
            for (Contact contact : contacts) {
                if (contact.getFirstName().contains(term) || contact.getLastName().contains(term) ||
                        contact.getPhone().contains(term) || contact.getEmail().contains(term)) {
                    result.add(contact);
                }
            }
        } else {
            for (String label : labels) {
                List<Contact> contactsForLabel = this.labels.get(label);
                if (contactsForLabel != null) {
                    for (Contact contact : contactsForLabel) {
                        if (contact.getFirstName().contains(term) || contact.getLastName().contains(term) ||
                                contact.getPhone().contains(term) || contact.getEmail().contains(term)) {
                            result.add(contact);
                        }
                    }
                }
            }
        }
        return result;
    }
    public void addContact(Contact contact, String... labels) {
        if (!contacts.contains(contact)) contacts.add(contact);
        if (labels.length == 0) return;
        for (var label : labels) {
            if (!this.labels.containsKey(label)) {
                this.labels.put(label, new LinkedList<>());
            }
            var contactsLabel = this.labels.get(label);

            if (!contactsLabel.contains(contact)) {
                contactsLabel.add(contact);
            }
        }
    }


    public boolean isDuplicate(Contact contact){
        for (Contact existingContact : contacts){
            if (existingContact.getPhone().equals(contact.getPhone()) || existingContact.getEmail().equals(existingContact.getEmail())){
                return true;
            }
        }
        return false;
    }
    public void removeContact(Contact contact) {
        contacts.remove(contact);
        labels.values().forEach(contacts -> contacts.remove(contact));
    }

    public int size() {
        return contacts.size();
    }

    public boolean isEmpty() { return contacts.isEmpty(); }

}
