package springframework.msscbeer.web.controller;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String beerNotFound) {
		super(beerNotFound);
	}
}
