package com.bozturk.idle.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bozturk.idle.dto.IdDto;
import com.bozturk.idle.dto.ListingDto;
import com.bozturk.idle.dto.UserListingDto;
import com.bozturk.idle.model.Category;
import com.bozturk.idle.model.Product;
import com.bozturk.idle.model.User;
import com.bozturk.idle.model.UserListing;
import com.bozturk.idle.model.UserListingPhoto;
import com.bozturk.idle.repository.CategoryRepository;
import com.bozturk.idle.repository.ProductRepository;
import com.bozturk.idle.repository.UserListingPhotoRepository;
import com.bozturk.idle.repository.UserListingRepository;
import com.bozturk.idle.repository.UserRepository;
import com.bozturk.idle.repository.UserStoreRepository;
import com.bozturk.idle.service.StoreService;
import com.bozturk.idle.service.UserPrincipal;

@Controller
public class ListingController extends MainController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserListingRepository listingRepository;

    @Autowired
    private UserListingPhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStoreRepository userStoreRepository;

    @Autowired
    private StoreService storeService;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user/listing", method = RequestMethod.GET)
    public ModelAndView getListing(@RequestParam(required = false) Long listingId, @RequestParam(required = false) Long cat1, @RequestParam(required = false) Long cat2,
            @RequestParam(required = false) Long cat3) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("stores", storeService.getUserStores());

        UserListing listing = new UserListing();
        ListingDto listingDto = new ListingDto();

        Set<Category> cat1s = categoryService.getCategoryData(1);
        listingDto.setCat1s(cat1s);

        if (listingId != null) {
            listing = listingRepository.findOne(listingId);
            listingDto.setListingId(listing.getListingId());

            Product product = productRepository.findOne(listing.getProduct().getProductId());
            listingDto.setProductId(product.getProductId());
            cat3 = product.getCategoryId();
            cat2 = categoryRepository.findById(cat3).getParentId();
            cat1 = categoryRepository.findById(cat2).getParentId();
            listingDto.setCat1(cat1);
            listingDto.setCat2(cat2);
            listingDto.setCat3(cat3);

            listingDto.setStoreId(listing.getStore().getStoreId());

            listingDto.setMake(product.getMake());
            listingDto.setModel(product.getModel());
            listingDto.setSerial(product.getSerial());
            listingDto.setBarcode(product.getBarcode());

            listingDto.setDescription(listing.getDescription());
            listingDto.setHeader(listing.getHeader());
            listingDto.setCount(listing.getCount());
            listingDto.setPhone(listing.getPhone());
            listingDto.setPrice(listing.getPrice());
            listingDto.setState(listing.getState());
            /*
             * if (product.getSideCategoryId()!=null) { Long cat3Side =
             * product.getSideCategoryId(); Long cat2Side =
             * categoryRepository.findById(cat3Side).getParentId(); Long
             * cat1Side = categoryRepository.findById(cat2Side).getParentId();
             * listingDto.setCat1Side(cat1Side);
             * listingDto.setCat2Side(cat2Side);
             * listingDto.setCat3Side(cat3Side);
             * modelAndView.addObject("cat1Side", cat1Side);
             * modelAndView.addObject("cat2Side", cat2Side);
             * modelAndView.addObject("cat3Side", cat3Side);
             * 
             * Set<Category> cat2Sides =
             * categoryService.getCategoryDataByParentCategory(cat1Side);
             * Set<Category> cat3Sides =
             * categoryService.getCategoryDataByParentCategory(cat2Side);
             * listingDto.setCat2Sides(cat2Sides);
             * listingDto.setCat3Sides(cat3Sides);
             * modelAndView.addObject("cat2Sides", cat2Sides);
             * modelAndView.addObject("cat3Sides", cat3Sides); }
             */
        }

        modelAndView.addObject("cat1s", cat1s);
        // modelAndView.addObject("cat1Sides", cat1s);

        if (cat1 != null) {
            listingDto.setCat1(cat1);
            Set<Category> cat2s = categoryService.getCategoryDataByParentCategory(cat1);
            listingDto.setCat2s(cat2s);

            modelAndView.addObject("cat1", cat1);
            modelAndView.addObject("cat2s", cat2s);
        }
        if (cat2 != null) {
            listingDto.setCat2(cat2);
            Set<Category> cat3s = categoryService.getCategoryDataByParentCategory(cat2);
            listingDto.setCat3s(cat3s);
            modelAndView.addObject("cat2", cat2);
            modelAndView.addObject("cat3s", cat3s);
        }
        if (cat3 != null) {
            listingDto.setCat3(cat3);
            modelAndView.addObject("cat3", cat3);
        }

        modelAndView.addObject("listingDto", listingDto);
        modelAndView.setViewName("/user/listing");
        addMissingObjects(modelAndView);
        return modelAndView;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user/listings", method = RequestMethod.GET)
    public ModelAndView getListings() {

        ModelAndView modelAndView = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userP = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findByEmail(userP.getUsername());

        List<UserListingDto> dtos= new ArrayList<UserListingDto>();
        List<UserListing> listings = listingRepository.findByUserId(user.getId());
        for (UserListing userListing : listings) {
            UserListingDto dto = new UserListingDto();
            BeanUtils.copyProperties(userListing, dto);
            UserListingPhoto photo = photoRepository.findByListingIdAndPorder(userListing.getListingId(), 1);
            if (photo!=null) {
                dto.setContent(photo.getContent());
            }
            dtos.add(dto);
        }
        
        modelAndView.addObject("listings", dtos);
        modelAndView.addObject("idDto", new IdDto());
        modelAndView.addObject("products", new ArrayList<Product>());
        modelAndView.addObject("categoryId", 0L);
        modelAndView.setViewName("/user/listings");
        addMissingObjects(modelAndView);
        return modelAndView;
    }
    
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user/purchases", method = RequestMethod.GET)
    public ModelAndView getPurchases() {

        ModelAndView modelAndView = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userP = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findByEmail(userP.getUsername());

        List<UserListingDto> dtos= new ArrayList<UserListingDto>();
        List<UserListing> listings = listingRepository.findByBuyerId(user.getId());
        for (UserListing userListing : listings) {
            UserListingDto dto = new UserListingDto();
            BeanUtils.copyProperties(userListing, dto);
            UserListingPhoto photo = photoRepository.findByListingIdAndPorder(userListing.getListingId(), 1);
            if (photo!=null) {
                dto.setContent(photo.getContent());
            }
            dtos.add(dto);
        }
        
        modelAndView.addObject("listings", dtos);
        modelAndView.addObject("idDto", new IdDto());
        modelAndView.addObject("products", new ArrayList<Product>());
        modelAndView.addObject("categoryId", 0L);
        modelAndView.setViewName("/user/purchases");
        addMissingObjects(modelAndView);
        return modelAndView;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user/listing", method = RequestMethod.POST)
    public ModelAndView setListing(@Valid ListingDto listingDto, BindingResult result, ModelAndView modelAndView, @RequestParam(value = "action", required = true) String action) {

        UserListing listing = null;
        if (listingDto.getListingId() != null && listingDto.getListingId() != 0) {
            listing = listingRepository.findOne(listingDto.getListingId());
            if (action.equals("delete")) {
                if (listing.getState().equals("C")){
                    listingRepository.delete(listingDto.getListingId());
                    return getListings();
                }
                else {
                    result.reject("errorMessage","Silinemez");
                    return getListing(listing.getListingId(), listingDto.getCat3(), listingDto.getCat2(), listingDto.getCat1());
                }
            }
        } else {
            listing = new UserListing();
        }
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userP = (UserPrincipal) authentication.getPrincipal();
        User user = userRepository.findByEmail(userP.getUsername());
        listing.setUser(user);
        listing.setStore(userStoreRepository.findOne(listingDto.getStoreId()));
        listing.setProduct(productRepository.findByCategoryIdAndMakeAndModelAndSerial(listingDto.getCat3(), listingDto.getMake(), listingDto.getModel(), listingDto.getSerial()));

        listing.setActive(true);
        listing.setCount(listingDto.getCount());
        listing.setDescription(listingDto.getDescription());
        listing.setHeader(listingDto.getHeader());
        listing.setPhone(listingDto.getPhone());
        listing.setPrice(listingDto.getPrice());
        listing.setState("E");
        listingRepository.save(listing);

        if (action.equals("save")) {
            return getListings();
        } else {
            return getPhotos(listing.getListingId(), result, modelAndView);
        }

    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user/listingphoto", method = RequestMethod.GET)
    public ModelAndView getPhotos(Long listingId, BindingResult result, ModelAndView modelAndView) {

        Set<UserListingPhoto> photos = photoRepository.findByListing(listingId);
        for (UserListingPhoto listingPhoto : photos) {
            modelAndView.addObject("photo" + listingPhoto.getPorder(), listingPhoto.getContent());
        }
        UserListingPhoto photo = new UserListingPhoto();
        photo.setListingId(listingId);
        modelAndView.addObject("photoDto", photo);
        modelAndView.setViewName("/user/listingphoto");
        addMissingObjects(modelAndView);
        return modelAndView;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user/listingphoto")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView setPhotos(@RequestParam("imageData") String file1, @RequestParam("listingId") Long listingId, @RequestParam("porder") Integer porder, ModelAndView modelAndView) {

        // data:image/png;base64,iVBORw312312

        String mimeType = file1.substring(5, file1.indexOf(";"));
        
        UserListingPhoto photo = photoRepository.findByListingIdAndPorder(listingId, porder);
        if (photo==null)
            photo = new UserListingPhoto();

        try {
            if (file1.isEmpty()) {
                throw new IOException("Resim Kaydedilemedi");
            }

            photo.setListingId(listingId);
            photo.setContent(file1);
            photo.setPorder(porder);
            photo.setFileType(mimeType);
            photoRepository.save(photo);

        } catch (IOException e) {

        }

        // ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("photoDto", photo);
        modelAndView.setViewName("/user/listingphoto");
        addMissingObjects(modelAndView);
        return modelAndView;

    }

    /*
     * @PreAuthorize("hasRole('USER')")
     * 
     * @RequestMapping(value = "/user/listingphoto/image/{photoId}", produces =
     * MediaType.IMAGE_PNG_VALUE) public ResponseEntity<byte[]>
     * getImage(HttpServletResponse response, @PathVariable("photoId") Long
     * photoId) throws IOException {
     * 
     * final HttpHeaders headers = new HttpHeaders();
     * headers.setContentType(MediaType.IMAGE_PNG);
     * 
     * 
     * return new ResponseEntity<byte[]>(, headers, HttpStatus.OK); }
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user/listingphoto/image/{listingId}/{porder}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<String> getImageStr(HttpServletResponse response, @RequestParam("listingId") Long listingId, @RequestParam("porder") Integer porder) throws IOException {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        UserListingPhoto photo = photoRepository.findByListingIdAndPorder(listingId, porder);
        return new ResponseEntity<String>(photo.getContent(), HttpStatus.OK);
    }

}
