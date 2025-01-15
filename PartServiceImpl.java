@Service
public class PartServiceImpl implements PartService {

    private static final String FILE_IMPORT_PATH = "src/main/resources/xml/imports/parts.xml";

    private final PartRepository partRepository;

    private final SupplierRepository supplierRepository;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    private final XmlParser xmlParser;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }


    @Override
    public void seedParts() throws JAXBException {
        if (partRepository.count() == 0) {
            PartSeedRootDto partSeedRootDto = this.xmlParser.parse(PartSeedRootDto.class, FILE_IMPORT_PATH);

            for (PartSeedDto partSeedDto : partSeedRootDto.getPartSeedDtoList()) {
                if (!this.validationUtil.isValid(partSeedDto)) {
                    this.validationUtil.getViolations(partSeedDto)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }

                Part part = this.modelMapper.map(partSeedDto, Part.class);
                part.setSupplier(getRandomSupplier());
                this.partRepository.saveAndFlush(part);
                System.out.println();
            }
        }
    }

    private Supplier getRandomSupplier() {
        return this.supplierRepository
                .findById(ThreadLocalRandom.current()
                        .nextLong(1, this.supplierRepository.count() + 1)).get();
    }
}
