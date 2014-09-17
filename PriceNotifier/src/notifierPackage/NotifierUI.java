/**
 *Author		: 	Arun Kumar Konduru Chandra
 *Purpose		:	Zappos Challenge
 *Date			:	02/19/2014
 *Description 	:	Application using Zappos API that lets a user pick their desired product(s) 
 *					and then notifies them when the price hit at least 20% off the original price.*/


package notifierPackage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

//This class is used for implementing User Interface.
@SuppressWarnings("serial")
public class NotifierUI extends JFrame {

	private JPanel contentPane;
	protected static JList<String> list_brand;
	protected static JList<String> list_found;
	protected static JButton btnSearch;
	protected static JComboBox<String> comboBox_prod;
	protected static JComboBox<String> comboBox_gen;
	protected static JLabel lblStatus;
	protected static DefaultListModel<String> listModel;
	protected static JTextField txtBrand;
	protected static JTextField txtPrice;
	protected static JTextField txtOff;
	protected static JTextArea txtNotification;
	protected static JLabel lblImage;
	protected static ButtonGroup sort;
	protected static JRadioButton rdbtnLatest;
	protected static JRadioButton rdbtnoff;
	
	//Create the frame and add components into it.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NotifierUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 30, 620, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblProduct.setBounds(38, 95, 66, 14);
		lblProduct.setHorizontalAlignment(JLabel.RIGHT);
		contentPane.add(lblProduct);
		
		JLabel lblBrand = new JLabel("Brand");
		lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblBrand.setBounds(401, 70, 46, 14);
		contentPane.add(lblBrand);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblGender.setBounds(38, 143, 66, 14);
		lblGender.setHorizontalAlignment(JLabel.RIGHT);
		contentPane.add(lblGender);
		
		JLabel lblSort = new JLabel("Sort By");
		lblSort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSort.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSort.setBounds(45, 211, 59, 14);
		contentPane.add(lblSort);
		
		comboBox_prod = new JComboBox();
		comboBox_prod.setToolTipText("Pick your product.\r\n");
		comboBox_prod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIOperations.changeBrandsOnProductChange();
				lblStatus.setText("Brands updated based on selected product.");
			}
		});
		comboBox_prod.setBackground(Color.LIGHT_GRAY);
		comboBox_prod.setModel(new DefaultComboBoxModel(new String[] {"Select Product", "Shoes", "Clothings", "Bags", "Accessories", "Eyewear", "Beauty", "Jewelry", "Watches", "Sporting Goods", "Home"}));
		comboBox_prod.setBounds(114, 94, 127, 20);
		contentPane.add(comboBox_prod);
		
		comboBox_gen = new JComboBox();
		comboBox_gen.setToolTipText("Pick your gender.");
		comboBox_gen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIOperations.changeBrandsOnGenderChange();
				lblStatus.setText("Brands updated based on selected gender.");
			}
		});
		comboBox_gen.setBackground(Color.LIGHT_GRAY);
		comboBox_gen.setModel(new DefaultComboBoxModel(new String[] {"Select Gender", "Men", "Women", "Boys", "Girls"}));
		comboBox_gen.setBounds(114, 142, 127, 20);
		contentPane.add(comboBox_gen);
		
		//JPanel to add a JList to get brand from user.
		JPanel panel_brand = new JPanel();
		panel_brand.setBounds(280, 95, 299, 199);
		contentPane.add(panel_brand);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_brand.add(scrollPane);
		
		list_brand = new JList();
		list_brand.setToolTipText("Press CTRL+LEFT MOUSE click for multiple selections.");
		list_brand.setBackground(Color.LIGHT_GRAY);
		list_brand.setVisibleRowCount(10);
		list_brand.setModel(new AbstractListModel() {
			String[] values = new String[] {"!iT Collective", "10 Crosby Derek Lam", "10 Strawberry Street", "2(X)IST", "2XU", "525 america", "686", "7 Diamonds", "7 For All Mankind", "9five", "A Gold E", "A. Testoni", "ABS Allen Schwartz", "Acorn", "adidas", "Adrianna Papell", "Adrienne Vittadini", "Aerin", "Aerobed", "Aerosoles", "Aetrex", "Affliction", "AG Adriano Goldschmied", "Agatha Ruiz De La Prada Kids", "Agave Denim", "Agent Provocateur", "AHAVA", "Ahnu", "AIGLE", "ALDO", "Alegria", "Alejandra Sky", "Alexander McQueen", "Alexandra Ferguson", "Alien Workshop", "All-Clad", "Allen Allen", "Allen-Edmonds", "Almost", "ALO", "Alternative Apparel", "Altra Zero Drop Footwear", "AmeriBag, Inc.", "American West", "Amiana", "Analog", "Anarchy Eyewear", "AND1", "Anita", "Anna Sui", "Anne Klein", "Annie", "Anon", "Anthony For Men", "Anuschka Handbags", "Anyi Lu International", "Appaman Kids", "Aquatalia by Marvin K.", "ara", "Aramis", "Aravon", "Arc'teryx", "Arche", "Archipelago Botanicals", "Arcopedico", "Ariat", "Armani Jeans", "Armani Junior", "Arnette", "Artland", "ASH", "Ashworth", "ASICS", "Asolo", "Aster Kids", "Athalon", "Athena Alexander", "Athena", "Atlas", "Authentic Apparel", "Autumn Cashmere", "Avanti", "Avia", "AX Armani Exchange", "Ayana Designs", "B Brian Atwood", "b.tempt'd", "Baby Deer", "Baby Jogger", "BabyBjorn", "Badgley Mischka", "Baffin", "Baggallini", "Bailey 44", "Balmain", "Bandolino", "Barefoot Dreams", "Bass", "Basta", "Bates Footwear", "BB Dakota", "BC Footwear", "BCBGeneration", "BCBGMAXAZRIA", "Beach Bunny", "Beach Riot", "Beatriz Ball", "Beats By Dre", "Beauty Blender", "BECCA by Rebecca Virtue", "Bed Head", "Bed Stu", "BedHead", "Bedroom Athletics", "Beeko", "Bella-Vita", "Belli", "Ben Minkoff", "Ben Sherman", "Bern", "Bernardo", "Betsey Johnson", "Beyond Yoga", "BIA Cordon Bleu", "Big Agnes", "Big Buddha", "Big Star", "Bikkembergs", "Billabong", "BIOELEMENTS", "Birkenstock", "Biscotti", "Black Diamond", "Blank NYC", "Bleu Rod Beattie", "Blind", "Bliss", "Blissliving Home", "Bloch", "Blowfish", "Blundstone", "Boast", "Boconi Bags and Leather", "Bodum", "Body Glove", "Bogs", "Bolle", "BOOTIGHTS", "Born", "Bosca", "BOSS Hugo Boss", "Bostonian", "Botkier", "Bottega Veneta", "Boucheron", "Bouquets", "Breil Milano", "Brenthaven", "Breville", "Bri Seeley", "Bric's U.S.A.", "Briggs & Riley", "Brighton", "Brigitte Bailey", "Brixton", "Brooke Rodd", "Brooks", "BRUNO MAGLI", "Buffalo David Bitton", "Bugatchi", "BULA", "Bulova", "Burberry", "Burton", "Butter London", "Bvlgari", "C&C California", "Call it SPRING", "Callaway", "Calphalon", "Calvin Klein", "CamelBak", "Camper", "Caparros", "Capezio", "Capita", "Capresso", "Cargo", "Carhartt", "CARLOS by Carlos Santana", "Carole Hochman", "Carolina Herrera", "Carrera", "Cartier", "Carve Designs", "Caso", "Caterpillar", "Catwalk", "Cayetano Legacy Collection", "Celebrity Fragrances", "Celtek", "CEP", "Cesare Paciotti", "Chaco", "Chan Luu", "Charles by Charles David", "Charriol", "Chaser", "Cheap Monday", "Chef's Choice", "CHI Home", "Chicago Skates", "Chinese Laundry", "Chippewa", "Chloe", "Chooka", "CHOOZE", "Christin Michaels", "Christopher Blue", "Chrome", "Cienta Kids Shoes", "Cinch", "Circa Joan & David", "Circa", "Citizen Watches", "CJ by Cookie Johnson", "Clae", "Clarks", "Clean", "COACH", "Coal", "Cobb Hill", "Cobian", "Cole Haan", "Coloriffics", "Columbia", "Commando", "Comptoir Sud Pacifique", "Converse", "The Cool People", "COOLA Suncare", "Cordani", "Corso Como", "Cosabella", "Costa", "Costume National", "Crave by Carli Rae Vergamini", "Creative Recreation", "Creature", "Crocs", "Crooks & Castles", "Croscill", "Cruel", "Crumpler", "Cuisinart", "Culture Phit", "Cushe", "CW-X", "D&G", "Dakine", "Dale of Norway", "Dan Post", "Danica Studio", "Daniel Green", "Danner", "Dansk", "Dansko", "Darkstar", "David Tate", "David Tutera", "Davidoff", "DC", "DDF", "De Soto", "Deborah Lippmann", "Dee Berkley", "Deer Stags", "DeLatori", "Delivering Happiness", "Delman", "DeLonghi", "Delsey", "Dermelect Cosmeceuticals", "Desigual", "Destroyer", "Dexter", "Diadora", "Diane Von Furstenberg", "Diaper Dude", "Diba", "Dickies", "Diesel", "Dimmi Footwear", "Dingo", "Dirty Laundry", "Dittos", "DKNY", "DL1961", "Dockers", "Dogeared", "Dolce & Gabbana", "Dolce Vita", "Donald J Pliner", "Donna Karan", "Donna Morgan", "Dooney & Bourke", "Double D Ranchwear", "Down Etc.", "Dr. Dennis Gross Skincare", "Dr. Martens", "Dr. Scholl's", "Dragon Alliance", "Drymax Sport Socks", "DSQUARED2", "Dunham", "Durango", "Dusters", "DV8", "DVS Shoe Company", "Dyeables", "E! Live from the Red Carpet", "Eagle Creek", "Earth", "Eastland", "Easy Spirit", "ECCO", "Echo Design", "Ektelon", "El Naturalista", "Electric Eyewear", "Element", "Elephantito", "Elie Tahari", "Elite", "Eliza J", "Elizabeth and James", "Ella Moss", "Ellen Tracy", "Elliott Lucca", "Emerica", "Emeril", "Emile Henry", "Emily Elizabeth Jewelry", "Emporio Armani", "EMU Australia", "Enjoi", "Enzo Angiolini", "Eric Michael", "Ermenegildo Zegna", "ESQ Movado", "Essie", "etnies", "Eugenia Kim", "EVOLV", "ExOfficio", "eyebobs", "Fagor", "Fallen", "Famous Stars & Straps", "Faviana", "Favorite Characters", "Feetures", "Fekkai", "Fendi Kids", "Fergie", "Salvatore Ferragamo", "Fidji", "Fig Clothing", "Filson", "Finn Comfort", "FitFlop", "Fitzwell", "Five Ten", "Vibram FiveFingers", "fiveloaves twofish", "Fj\u00E4llr\u00E4ven", "Flexus", "Flip", "Flogg", "Flojos", "Florsheim", "Flux", "Foamtreads", "Foley & Corinna", "Foot Petals", "FootJoy", "FootMates", "Forum", "Fossil", "Fox River", "Fox", "Franchi Handbags", "Francine", "Franco Sarto", "Fratelli Rossetti", "Fratelli", "Fred Perry", "Free People", "Freebird", "Freestyle", "Freewaters", "French Connection", "French Sole", "Frye", "Fuel Belt", "Furla", "Fusion Beauty", "G by GUESS", "G-Shock", "G-Star", "G.I.A. Luxe", "Gabor", "Gabriella Rocha", "Gaiam", "Gargoyles", "Garmont", "GBX", "Generic Surplus", "Gentle Souls", "George Gina & Lucy", "Geox", "Giesswein", "Giorgio Brutini", "Giro", "Giuseppe Zanotti", "Givenchy", "Glam Rock", "Glamglow", "Globe", "Gnu", "Gola", "Gold Coast", "GoLite", "Goodhew", "Goorin Brothers", "Gordon Rush", "Gorham", "Gorilla", "gorjana", "Grace Hats", "Graham and Spencer", "Gravati", "Gravis", "Grazie", "Gregory", "Gucci", "GUESS", "GUNNAR Optiks", "Gypsy SOULE", "Habitat", "Hadaki", "Haflinger", "HAI", "Haiku", "Hairdo", "Hale Bob", "Hanky Panky", "Hanro", "Hanwag", "Harbor House", "Harley-Davidson", "Harveys Seatbelt Bag", "Hat Attack", "Hatley Kids", "Havaianas", "Heelys", "Helle Comfort", "Helly Hansen", "Herschel Supply Co.", "Hex", "Heys", "Hi-Tec", "High Sierra", "Highbury", "Hobo", "Hoka One One", "Hollywood Fashion Secrets", "Home Source International", "Horny Toad", "Hot Chillys", "House of Harlow 1960", "House of Marley", "Howe", "ECCO", "Hudson", "HUE", "Hugger Mugger", "Hunter", "Hurley", "Hush Puppies", "Icebreaker", "Icon", "Incase", "Injinji", "inov-8", "Inspired by Claire Jane", "InterDesign", "Irish Setter", "Isaac Mizrahi New York", "Isola", "Italia Independent", "Ivanka Trump", "IVI", "Ivy & Blu Maggy Boutique", "J MEN by Jamie Sadock", "J-41", "J. Renee", "J.Fold", "Jack Rogers", "Jack Spade", "Jag Jeans", "Jambu", "James Murray", "Jamie Sadock", "Jane & Bleecker", "Jane Tran", "JanSport", "JD Fisk", "Jean Paul Gaultier", "Jefferies Socks", "Jeffery-West", "Jessica McClintock", "Jessica Simpson", "Jetboil", "JF Lazartigue", "JHaus", "Jimmy Choo", "Joe's Jeans", "John Varvatos", "Johnston & Murphy", "Joie", "Josef Seibel", "Joseph Joseph", "Josie", "Ju-Ju-Be", "Judith Jack", "Juicy Couture", "Julbo Eyewear", "Jumping Jacks Kids", "June Jacobs Spa Collection", "Just Cavalli", "Justin", "K-Swiss", "K2 Skates", "Kaenon", "KAMALIKULTURE", "Kamik", "KangaROOS", "Kangol", "Karen Kane", "Karen Neuburger", "Kate Mack", "Kate Spade New York", "KAVU", "Keds", "Keen", "Kelsi Dagger", "Kelty", "Kendra Scott", "Kennebunk Home", "Kenneth Cole", "Kenneth Jay Lane", "kensie", "Keurig", "Khombu", "Kid Express", "Kimberly Goldson", "King Baby Studio", "Kipling", "KitchenAid", "Kiyonna", "Klogs USA", "KNOMO London", "Kooba", "Kork-Ease", "KR3W", "Krickette", "Krups", "Kuhl", "KUT from the Kloth", "L*Space", "L-R-G", "L.A.M.B.", "L.B. Evans", "La Blanca", "La Canadienne", "La Mer", "La Plume", "La Sportiva", "Lab Series", "Lacoste", "Lakai", "LAmade", "Lanvin", "Laredo", "Larry Levine", "Lassen", "Laundry by Shelli Segal", "LAUREN Ralph Lauren", "Layla", "Le Chameau", "Le Creuset", "Le Mystere", "Le Specs", "le top", "Leather Couture by Jessica Galindo", "Leatherock", "Leki", "Lekue", "Lenox", "LeSportsac", "Levi's\u00AE Made & Crafted", "Levi's\u00AE", "Lib Tech", "Liebeskind", "Life is good", "LifeStride", "Lifetime Collective", "Lilly Pulitzer", "Linea Pelle", "Lipault Paris", "Lisa Taranto", "Little Giraffe", "Livie & Luca", "Lloyd", "Lodis Accessories", "Loeffler Randall", "Lole", "LORAC", "Loudmouth Golf", "Louis Garneau", "LOVE Moschino", "Love Quotes", "Lowa", "Lucchese", "Lucky Brand", "Lucy Love", "Lucy", "Lugz", "Luichiny", "Luigi Bormioli", "Luli Fama", "Lumiani International Collection", "Luna Luna Copenhagen", "Luxury Rebel", "Lysse", "M&F Western", "Maaji", "Macbeth", "Madden Girl", "MadPax", "Maggy London", "Magicsuit", "Magnanni", "Magnum", "Manduka", "Manzella", "Mara Hoffman", "Marc by Marc Jacobs", "Marc Ecko", "Marc Jacobs", "Marc New York by Andrew Marc", "Marmot", "Mary Frances", "Massimo Matteo", "Matisse", "Maui Jim", "Mavi Jeans", "McQ", "Me Too", "Mek Denim", "Mel by Melissa", "Mele", "Melissa Odabash", "Members Only", "Mephisto", "Merrell", "Mezlan", "MIA", "Michael Antonio", "Michael Kors Collection", "MICHAEL Michael Kors", "Michael Stars", "Michele", "MINKPINK", "Minnetonka", "Miracle Skin Transformer", "Miraclebody Jeans", "Miraclesuit", "Missoni", "Mitchell & Ness", "Miz Mooz", "Mizuno", "MM6 Maison Martin Margiela", "Mod-o-doc", "Momentum by St. Moritz", "Montrail", "Moods of Norway", "Morgan&Milo Kids", "Moschino", "Mosey", "Mountain Hardwear", "Moving Comfort", "MOZO", "MPG Sport", "MSR", "Mulholland Brothers", "Munro American", "My Flat In London", "Mystery", "Nanette Lepore", "Naot Footwear", "Nathan", "Native Eyewear", "Native Shoes", "Natori", "Naturalizer", "Naturino", "NAU", "Naughty Monkey", "Nautica", "Naya", "Neff", "Neil M.", "New Balance", "New Era", "Newton Running", "NIC+ZOE", "Nicole Miller", "Nike", "Nina Ricci", "Nina", "Nine West", "Nixon", "Nordic Ware", "The North Face", "NoSoX", "Not Rated", "Nunn Bush", "Nurse Mates", "NUX", "NYDJ", "O'Neill", "Oakley", "Obermeyer", "Obey", "Oboz", "Oenophilia", "OGIO", "Old Friend", "Old Gringo", "Old West Boots", "OluKai", "Omega", "Oneida", "Onex", "OnGossamer", "The Original Muck Boot Company", "Original Penguin", "ORLY", "Oscar Blandi", "Oscar de la Renta", "Osiris", "Osprey", "OTZ", "Outdoor Research", "Overland Equipment", "OXO", "P.J. Salvage", "Pablosky Kids", "Pacsafe", "Paige", "Palladium", "Pampili", "Paris Hilton", "Parker", "Patagonia", "Patricia Green", "Patricia Nash", "Paul Green", "Paul Smith", "Paula Dorf", "Pazitos", "Pearl Izumi", "pediped", "Pedro Garcia", "Pendleton", "Penny Loves Kenny", "Penny", "Perry Ellis", "Persol", "petunia pickle bottom", "Petzl", "PF Flyers", "Philip Stein", "Philosophy", "Phyto", "Picnic Time", "Pierre Balmain", "Pikolinos", "Pink & Pepper", "Pink Lotus", "Pistil", "Pleaser USA", "Polar USA", "Polaroid Eyewear", "Polo Ralph Lauren", "POPbeauty", "Pour La Victoire", "Powerstep", "Prada", "Prana", "Premier Comfort", "Primigi Kids", "Prince", "Privo by Clarks", "Pro-Keds", "Pro-Tec", "Proenza Schouler", "Profile Design", "Project Canvas", "Propet", "PUMA", "purminerals", "Quiksilver", "R.S.V.P. International", "Rachel Kids", "Rachel Pally", "Rachel Roy", "Rachel Zoe", "radii Footwear", "RAEN Optics", "Rafe New York", "Ragg Kids", "Ralph Lauren Collection", "Raquelle", "Ray-Ban", "Razor", "Rebecca Minkoff", "Rebecca Taylor", "RED Valentino", "Red Wing Heritage", "Reebok", "Reed & Barton", "Reef", "Report", "Revo", "Riedel", "Rieker", "Rimowa", "Rip Curl", "Robeez", "Robert Clergerie", "Robert Graham", "Robert Wayne", "Roberto Cavalli", "Roberto Coin", "Rocawear", "Rock and Roll Cowboy", "Rock and Roll Cowgirl", "Rock Revival", "Rocket Dog", "Rockport", "Rodd & Gunn", "Rollerblade", "Romantic Soles", "Romika", "Roper", "Rowenta", "Roxbury Park", "Roxy", "Royal Robbins", "rsvp", "Ruffwear", "RVCA", "Ryka", "S-Factor", "Sabre Vision", "Salomon", "Salt Water Sandal by Hoy Shoes", "Salvatore Ferragamo", "Sam Edelman", "Samsonite", "San Diego Hat Company", "Sanctuary", "Sango", "Sanita", "Santa Cruz", "Santoni", "Sanuk", "Saucony", "Sbicca", "Scarpa", "School Issue", "Schutz", "Scotch & Soda", "Scott Hawaii", "Scully", "Seafolly", "SeaVees", "Sebago", "See by Chloe", "See Kai Run Kids", "Seirus", "Sergio Rossi", "Serta", "Sesto Meucci", "Seychelles", "SHEEX", "Shellys London", "Sherpani", "Shimano", "Shock Absorber", "Shoshanna", "Shun", "SIDI", "Sigerson Morrison", "Signal", "Silvia Bours", "simplehuman", "Siwy Denim", "Skagen", "SKECHERS", "Skip Hop", "Skirt Sports", "Skooba Design", "Skullcandy", "skyn ICELAND", "Smartwool", "Smith Optics", "Sockwell", "Sofft", "Soft Style", "Softspots", "SoftWalk", "SOL Republic", "SOLE", "Soleus", "SOLO", "SOREL", "Soybu", "Spalding", "Spanx", "SpaRitual", "Speedo", "Spenco", "Sperry Top-Sider", "Spiegelau", "Spiewak", "Spira", "SpiritHoods", "Splendid", "Spring Step", "Spy Optic", "Spyder", "Stacy Adams", "Stance", "Stella McCartney", "StepChild", "Stephen Webster", "Stetson", "Steve Madden", "Stila", "STM Bags", "Stonz", "Stride Rite", "Strider", "Strivectin", "Stuart Weitzman", "StyleStalker", "SunCloud Polarized Optics", "Super", "Superfeet", "Superfit", "Superga", "Supergoop", "Supra", "Susana Monaco", "Suunto", "Swedish Hasbeens", "Victorinox", "T3 Gear", "T3", "Tahari by ASL", "Tail Activewear", "taos Footwear", "Tart", "Taryn Rose", "Tasha Polizzi", "Tavik", "Tbags Los Angeles", "Tecnica", "Ted Baker", "Teva", "The Cool People", "The North Face", "The Original Muck Boot Company", "The Sak", "theBalm", "Theory", "Therm-a-Rest", "Think!", "thirtytwo", "Thorlos", "Thorogood", "Three Dots", "Tibi", "Tifosi Optics", "Tignanello", "Timberland", "Timbuk2", "Timex", "Timi & Leslie Diaper Bags", "Tingley Overshoes", "Tkees", "To Boot New York", "Toes on the Nose", "Tommy Bahama", "Tommy Hilfiger", "Tony Lama", "Tonya Gross Millinery", "Toobydoo", "Torino Leather Co.", "Tory Burch", "Touch Ups", "Townsen", "Trask", "Travelpro", "Travis Mathew", "Tretorn", "Trigger Point", "Trina Turk", "Triple Eight", "Trotters", "Troy Lee Designs", "True Religion", "Trukfit", "Tsubo", "Tsukihoshi Kids", "Tubbs", "Tucker", "Tumi", "Tundra Boots", "TW Steel", "Tweezerman", "Type Z", "TYR", "U-Boat", "UFC", "UGG", "Ultimate Direction", "Umi Kids", "Under Armour", "Union", "Unique Vintage", "United Colors of Benetton Kids", "Us Angels", "Uurmi", "Vaneli", "Vans", "Vasque", "Velvet by Graham and Spencer", "Venettini Kids", "Venius", "Vera Bradley", "Vera Wang", "Versace", "Vestal", "Via Spiga", "Vibram FiveFingers", "Vicini", "Victorinox", "Vidorreta", "Vigotti", "Viktor & Rolf", "Vilma Kere Designs", "Vince Camuto", "Vince", "Vineyard Vines", "VIONIC with Orthaheel Technology", "Vitamin A Swimwear", "Vitamix", "Vivanz", "Vivienne Westwood", "Vivobarefoot", "Vix", "Vogue", "VOLATILE", "Volcom", "VonZipper", "Wacoal", "Waechtersbach", "Walk-Over", "Walking Cradles", "Wanted", "Ward's Boxing Club NYC", "Waring Pro", "Stuart Weitzman", "Westcomb", "Western Chief Kids", "Wigwam", "Wildfox", "Will Leather Goods", "Wilson", "Wolford", "Wolky", "Wolverine", "Woodlore", "Woolrich", "Worishofer", "Wrightsock", "Wusthof", "w\u00FCrkin stiffs", "XCVI", "Y's by Yohji Yamamoto", "YakTrax", "Yellow Box", "Yes To", "Yummie Tummie by Heather Thomson", "ZAC Zac Posen", "Zamberlan", "Zanerobe", "Zappos.com Gear", "Zeal Optics", "Zensah", "Zero", "Ziera", "Zirh", "Zojirushi", "Zoot Sports", "Zumba", "Zwilling J.A. Henckels"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list_brand);
		
		//JButton to perform SEARCH operation.
		btnSearch = new JButton("SEARCH");
		btnSearch.setToolTipText("Click to search for the product.");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatus.setText("Searching");
				UIOperations.clearProductInfo();
				UIOperations.addSearchResultToList();
			}
		});
		btnSearch.setFont(new Font("Forte", Font.PLAIN, 15));
		btnSearch.setBounds(99, 341, 106, 33);
		contentPane.add(btnSearch);
		
		JLabel lblZapposcom = new JLabel("ZAPPOS.com !");
		lblZapposcom.setFont(new Font("Forte", Font.BOLD, 26));
		lblZapposcom.setBounds(212, 11, 182, 33);
		contentPane.add(lblZapposcom);
		
		//JPanel to add a JList with JScrollPane to display the search results.
		JPanel panel_products = new JPanel();
		panel_products.setToolTipText("");
		panel_products.setBounds(10, 467, 384, 163);
		contentPane.add(panel_products);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_products.add(scrollPane_2);
		
		listModel = new DefaultListModel();
		list_found = new JList();
		list_found.setToolTipText("Select a product to know more about it.");
		list_found.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_found.setFont(new Font("Segoe UI", Font.BOLD, 11));
		list_found.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!list_found.isSelectionEmpty())
					UIOperations.showProductInfo();
			}
		});
		scrollPane_2.setViewportView(list_found);
		
		JLabel lblProductsFound = new JLabel("Products found");
		lblProductsFound.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblProductsFound.setBounds(128, 436, 113, 14);
		contentPane.add(lblProductsFound);
		
		JLabel lblBrand_1 = new JLabel("Brand");
		lblBrand_1.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblBrand_1.setBounds(458, 437, 46, 14);
		contentPane.add(lblBrand_1);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblPrice.setBounds(442, 492, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblOff = new JLabel("% Off");
		lblOff.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblOff.setBounds(516, 492, 46, 14);
		contentPane.add(lblOff);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBounds(24, 641, 538, 20);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//Components to display further information on search results.
		lblStatus = new JLabel("Ready");
		lblStatus.setBounds(0, 0, 538, 20);
		panel.add(lblStatus);
		
		txtBrand = new JTextField();
		txtBrand.setToolTipText("Not editable: Displays product brand.");
		txtBrand.setEditable(false);
		txtBrand.setBounds(401, 461, 164, 20);
		contentPane.add(txtBrand);
		txtBrand.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setToolTipText("Not Editable: Displays price of the product.");
		txtPrice.setEditable(false);
		txtPrice.setBounds(401, 517, 86, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		txtOff = new JTextField();
		txtOff.setToolTipText("Not Editable: Displays percentage off.");
		txtOff.setEditable(false);
		txtOff.setBounds(516, 517, 46, 20);
		contentPane.add(txtOff);
		txtOff.setColumns(10);
		
		//Text area to notify percentage of offers.
		txtNotification = new JTextArea("Notification:");
		txtNotification.setToolTipText("Not Editable: Notifies if product hits 20% off.");
		txtNotification.setEditable(false);
		txtNotification.setBackground(Color.LIGHT_GRAY);
		txtNotification.setColumns(5);
		txtNotification.setBounds(401, 551, 166, 55);
		contentPane.add(txtNotification);
		
		lblImage = new JLabel("");
		lblImage.setBounds(385, 305, 164, 121);
		contentPane.add(lblImage);
		
		//Radio buttons to sort results.
		rdbtnoff = new JRadioButton("%Off");
		rdbtnoff.setBounds(114, 209, 86, 23);
		contentPane.add(rdbtnoff);
		
		rdbtnLatest = new JRadioButton("Latest Product");
		rdbtnLatest.setBounds(114, 250, 120, 23);
		contentPane.add(rdbtnLatest);
		
		sort = new ButtonGroup();
		sort.add(rdbtnoff);
		sort.add(rdbtnLatest);
	}
}
