

   function sortParcel(value, sortdirection) {
   console.log("util :: value : " + value);
   console.log("util :: sortdirection : " + sortdirection);
    if (sortdirection =="ASC") { document.location='/parcel/list?sortby='+value+'&order=desc'; }
    else{ document.location='/parcel/list?sortby='+ value + '&order=asc';}
    }
   function sortParcelDocument(value, sortdirection) {
   console.log("util :: value : " + value);
   console.log("util :: sortdirection : " + sortdirection);
    if (sortdirection =="ASC") { document.location='/parceldocument/list?sortby='+value+'&order=desc'; }
    else{ document.location='/parceldocument/list?sortby='+ value + '&order=asc';}
    }
   function sortParcelOwnership(value, sortdirection) {
   console.log("util :: value : " + value);
   console.log("util :: sortdirection : " + sortdirection);
    if (sortdirection =="ASC") { document.location='/parcelownership/list?sortby='+value+'&order=desc'; }
    else{ document.location='/parcelownership/list?sortby='+ value + '&order=asc';}
    }

   function sortPerson(value, sortdirection) {
   console.log("util :: value : " + value);
   console.log("util :: sortdirection : " + sortdirection);
    if (sortdirection =="ASC") { document.location='/person/list?sortby='+value+'&order=desc'; }
    else{ document.location='/person/list?sortby='+ value + '&order=asc';}
    }
