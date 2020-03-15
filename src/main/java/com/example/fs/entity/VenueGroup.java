package com.example.fs.entity;


public class VenueGroup extends Group<CompactVenue> {

  private static final long serialVersionUID = -996401659508844800L;

  @Override
  public CompactVenue[] getItems() {
    return items;
  }
  
  private CompactVenue[] items;
}
