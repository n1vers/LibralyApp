package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.LiblaryCard;

import java.util.List;

public class LibralyCardService implements Service<LiblaryCard> {

    @Override
    public boolean add() {
        LiblaryCard libralyCard = LibralyCardAppHelper.create()
         if (libralyCard == null) return false;
         try {
             repository.save(libralyCard);
             return true;
         }catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
             return false;
         }
    }

    @Override
    public boolean print() {
        return LibraryCardAppHelper.printList(repository.load());
    }

    @Override
    public List<LiblaryCard> list() {
         return List.of();
    }
    public List<LiblaryCard> returnBook(){
        return repository.load();
    }
}
