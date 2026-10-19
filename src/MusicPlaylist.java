public class MusicPlaylist {

  // head: ilk şarkı, tail: son şarkı, current: şu an çalan şarkı
  private SongNode head;
  private SongNode tail;
  private SongNode current;

  // Başta liste boş, üçü de null
  public MusicPlaylist() {
    head = null;
    tail = null;
    current = null;
  }

  // Listenin sonuna şarkı ekle
  public void addSong(String title) {
    SongNode newNode = new SongNode(title);

    if (head == null) {
      // Liste boş: yeni düğüm hem baş, hem son, hem çalan şarkı
      head = newNode;
      tail = newNode;
      current = newNode;
    } else {
      // Liste dolu: tail'in arkasına bağla (iki yönlü)
      newNode.prev = tail;   // 1) yeni düğüm geriye eski tail'i göstersin
      tail.next = newNode;   // 2) eski tail ileriye yeni düğümü göstersin
      tail = newNode;        // 3) artık son düğüm yeni düğüm
    }
  }

  // Başlığa göre şarkı sil
  public void removeSong(String title) {
    // 1) Şarkıyı ara
    SongNode temp = head;
    while (temp != null && !temp.title.equals(title)) {
      temp = temp.next;
    }

    // 2) Bulunamadıysa mesaj ver ve çık
    if (temp == null) {
      System.out.println("The song isn't in the playlist");
      return;
    }

    // 3) Silinecek şarkı çalan şarkıysa current'ı önce güvenli yere taşı
    //    (önce sonrakine, sonraki yoksa öncekine; ikisi de yoksa null olur)
    if (current == temp) {
      if (temp.next != null) {
        current = temp.next;
      } else {
        current = temp.prev;
      }
    }

    // 4) Bağlantıları güncelle
    if (temp == head && temp == tail) {
      // Listedeki tek şarkı
      head = null;
      tail = null;
    } else if (temp == head) {
      // Baştan silme
      head = head.next;
      head.prev = null;
    } else if (temp == tail) {
      // Sondan silme
      tail = tail.prev;
      tail.next = null;
    } else {
      // Ortadan silme
      temp.prev.next = temp.next;
      temp.next.prev = temp.prev;
    }

    System.out.println("Removed: " + temp.title);
  }

  // Sonraki şarkıya geç
  public void nextSong() {
    if (current != null && current.next != null) {
      current = current.next;
      System.out.println("Playing: " + current.title);
    } else {
      System.out.println("Already at the last song of the playlist.");
    }
  }

  // Önceki şarkıya geç
  public void previousSong() {
    if (current != null && current.prev != null) {
      current = current.prev;
      System.out.println("Playing: " + current.title);
    } else {
      System.out.println("Already at the first song of the playlist.");
    }
  }

  // Çalan şarkıyı göster
  public void displayCurrentSong() {
    if (current == null) {
      System.out.println("No song is playing.");
    } else {
      System.out.println("Current Song: " + current.title);
    }
  }

  // Tüm listeyi baştan sona göster
  public void displayPlaylist() {
    SongNode temp = head;
    while (temp != null) {
      System.out.print(temp.title + " <-> ");
      temp = temp.next;
    }
    System.out.println("END");
  }
}
