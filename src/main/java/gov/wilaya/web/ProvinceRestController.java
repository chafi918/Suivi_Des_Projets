package gov.wilaya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.wilaya.dao.ProjetRepository;
import gov.wilaya.dao.ProvinceRepository;
import gov.wilaya.entities.Province;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin("*")
public class ProvinceRestController {
	
	@Autowired
	private ProvinceRepository provinceRepository;
	@Autowired
	private ProjetRepository projetRepository;
	
	@RequestMapping(value = "/province", method = RequestMethod.POST)
	public void ajouterProvince(@RequestBody Province province) {
		if (provinceRepository.findByName(province.getLibelleProvince()) == null) {
			provinceRepository.save(province);
		}
	}

	@RequestMapping(value = "/provinceBN", method = RequestMethod.GET)
	public Page<Province> getProvinceByName(@RequestParam(name = "name", defaultValue = "") String province,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return provinceRepository.searchByName(province, new PageRequest(page, size));
	}

	@RequestMapping(value = "/provinces", method = RequestMethod.GET)
	public List<Province> getProvinces() {
		return provinceRepository.findAll();
	}

	@RequestMapping(value = "/getAllProvinces", method = RequestMethod.GET)
	public Page<Province> getProvinces(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return provinceRepository.findAll(new PageRequest(page, size));
	}

	@RequestMapping(value = "province/{id}", method = RequestMethod.GET)
	public Province getProvince(@PathVariable Long id) {
		return provinceRepository.findOne(id);
	}
	
	@RequestMapping(value = "/province", method = RequestMethod.GET)
	public Province getProvinceByProjet(@RequestParam(name = "idProjet") Long idProjet) {
		return projetRepository.findOne(idProjet).getProvince();
	}

	@RequestMapping(value = "deleteProvince/{id}", method = RequestMethod.DELETE)
	public boolean supprimerProvince(@PathVariable Long id) {
		if (provinceRepository.findOne(id) != null) {
			provinceRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "updateProvince/{id}", method = RequestMethod.PUT)
	public boolean udpateProvince(@PathVariable Long id, @RequestBody Province province) {
		if (provinceRepository.findOne(id) != null) {
			province.setIdProvince(id);
			provinceRepository.save(province);
			return true;
		}
		return false;
	}

}
